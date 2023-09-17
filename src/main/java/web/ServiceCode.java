package web;

/**
 * 業務狀態碼(常量)的類
 */
public final class ServiceCode {

    /**
     * ok成功
     */
    public static final Integer ok = 20000;

    /**
     * 錯誤:請求格是有誤
     */
    public static final Integer ERR_BAD_REQUEST = 40000;

    /**
     * 錯誤:JWT數據錯誤,可能被竄改過
     */
    public static final Integer ERR_JWT_INVALID = 40001;

    /**
     * 錯誤:JWT數據錯過期
     */
    public static final Integer ERR_JWT_EXPIRED = 40300;

    /**
     * 錯誤:不存在(不存在數據)
     */
    public static final Integer ERR_NOT_FOUND = 40400;

    /**
     * 錯誤:衝突(出現衝突的數據)
     */
    public static final Integer ERR_CONFLICT = 40900;

    /**
     * 錯誤:插入失敗
     */
    public static final Integer ERR_INSERT = 50000;

    /**
     * 錯誤:刪除失敗
     */
    public static final Integer ERR_DELETE = 50001;

    /**
     * 錯誤:更新失敗
     */
    public static final Integer ERR_UPDATE = 50002;

    /**
     * 錯誤:未知錯誤或是異常
     */
    public static final Integer ERR_UNKNOWN = 59999;


}
