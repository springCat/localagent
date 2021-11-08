package org.springcat.localagent;


public class JavassistAop {

    private String cls;

    private String method;

    private String before;

    private String after;

    public JavassistAop(String cls, String method) {
        this.cls = cls;
        this.method = method;
    }

    public JavassistAop(String cls, String method, String before, String after) {
        this.cls = cls;
        this.method = method;
        this.before = before;
        this.after = after;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    @Override
    public String toString() {
        return "JavassistAop{" +
                "cls='" + cls + '\'' +
                ", method='" + method + '\'' +
                ", before='" + before + '\'' +
                ", after='" + after + '\'' +
                '}';
    }
}
