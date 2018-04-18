package test;

public class ss {

	/**
		 * 批量查询当前合同
		 * @param rentContractBaseList
		 * @return
		 */
		public List<RentContractBase> batchGetCurrentRentContractBase(List<RentContractBase> rentContractBaseList) {
			
			List<RentContractBase> curRentContractBaseList = new ArrayList<>();
			
			if (CollectionUtils.isEmpty(rentContractBaseList)) {
				return curRentContractBaseList;
			}
			
			List<String> rentContractCodeList = new ArrayList<>();
			
			for (RentContractBase rentContractBase : rentContractBaseList) {
				//判断该合同是否有可能存在当前合同
				if (!isExistCurrentRent(rentContractBase)) {
					continue;
				}
				if (CollectionUtils.isNotEmpty(rentContractBases)) {
					// 按租赁开始日排序
					Collections.sort(rentContractBases, new Comparator<RentContractBase>() {
						@Override
						public int compare(RentContractBase o1, RentContractBase o2) {
							return o1.getRentBeginDate().compareTo(o2.getRentBeginDate());
						}
					});
	
					for (RentContractBase contractBase : rentContractBases) {
						// 取失效未处理合同
						if (contractBase.getEffectStatus() == Constants.RentContract.EFFECT_STATUS_INVALID
								&& contractBase.getInvalidCode() == Constants.RentContract.INVALID_CODE_NOT_DEAL) {
							return contractBase.getRentContractCode();
						}
	
						// 取生效合同
						if (contractBase.getEffectStatus() == Constants.RentContract.EFFECT_STATUS_VALID) {
							return contractBase.getRentContractCode();
						}
	
						// 取即将生效合同
						if (contractBase.getEffectStatus() == Constants.RentContract.EFFECT_STATUS_PRE) {
							return contractBase.getRentContractCode();
						}
					}
				rentContractCodeList.add(rentContractBase.getPreviousContractCode());
			}
			
			if (CollectionUtils.isEmpty(rentContractCodeList)) {
				return curRentContractBaseList;
			}
			
			List<RentContractBase> tempRentContractList = rentContractBaseRepository.findByRentContractCodes(rentContractCodeList);
			
			for (RentContractBase rentContractBase : tempRentContractList) {
				if (rentContractBase.getEffectStatus() == Constants.RentContract.EFFECT_STATUS_VALID) {
					curRentContractBaseList.add(rentContractBase);
				}
			}
	
			return null;
			
			return curRentContractBaseList;
		}
}
