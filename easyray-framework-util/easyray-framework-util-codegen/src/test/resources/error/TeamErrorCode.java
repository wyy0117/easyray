
import com.easyray.common.exception.filter.ICustomErrorCode;

/**
 * @Date: 2020-02_22
 * @Author: wyy
 */
public enum TeamErrorCode implements ICustomErrorCode {

    private String _code;
    private String _reason;

    TeamErrorCode(String _code, String _reason) {
        this._code = _code;
        this._reason = _reason;
    }

    @Override
    public String code() {
        return _code;
    }

    @Override
    public String reason() {
        return _reason;
    }

}
