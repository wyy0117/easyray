
import com.easyray.common.exception.filter.ICustomErrorCode;

/**
 * @Date: ${DATE}
 * @Author: ${AUTHOR}
 */
public enum ${ENTITY}ErrorCode implements ICustomErrorCode {

    private String _code;
    private String _reason;

    ${ENTITY}ErrorCode(String _code, String _reason) {
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
