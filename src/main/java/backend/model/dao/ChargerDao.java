package backend.model.dao;

public interface ChargerDao {
    /**
     * 킬로와트 당 비용 계산 조회
     * @return 예상 비용
     * (비용 단가, 충전량)
     */
    int preCalculateCost(int kw_price, int charge_amount);
}
