package cn.com.lixiaoning.bean;

/**
 * 作者：yokin.li on 2017/10/25 10:37
 * 邮箱：86558543@qq.com
 */

public class FestFulBean<T> {

    private int status;
    private T data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
