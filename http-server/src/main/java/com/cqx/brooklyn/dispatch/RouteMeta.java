package com.cqx.brooklyn.dispatch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.lang.reflect.Method;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/11/10
 */
@AllArgsConstructor
@Data
public class RouteMeta {

    private String uriPattern;

    private String methodSupport;

    private Method method;


//==== do the equals,hashcode as lombok do  =========

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RouteMeta)) {
            return false;
        } else {
            RouteMeta other = (RouteMeta)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$uriPattern = this.getUriPattern();
                Object other$uriPattern = other.getUriPattern();
                if (this$uriPattern == null) {
                    if (other$uriPattern != null) {
                        return false;
                    }
                } else if (!this$uriPattern.equals(other$uriPattern)) {
                    return false;
                }

                Object this$methodSupport = this.getMethodSupport();
                Object other$methodSupport = other.getMethodSupport();
                if (this$methodSupport == null) {
                    if (other$methodSupport != null) {
                        return false;
                    }
                } else if (!this$methodSupport.equals(other$methodSupport)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof RouteMeta;
    }

    public int hashCode() {
        int result = 1;
        Object $uriPattern = this.getUriPattern();
        result = result * 59 + ($uriPattern == null ? 43 : $uriPattern.hashCode());
        Object $methodSupport = this.getMethodSupport();
        result = result * 59 + ($methodSupport == null ? 43 : $methodSupport.hashCode());
        return result;
    }

    public String toString() {
        return "RouteMeta(uriPattern=" + this.getUriPattern() + ", methodSupport=" + this.getMethodSupport() + ")";
    }
}
