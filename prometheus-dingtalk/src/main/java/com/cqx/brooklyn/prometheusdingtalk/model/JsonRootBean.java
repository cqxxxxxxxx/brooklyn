/**
 * Copyright 2020 bejson.com
 */
package com.cqx.brooklyn.prometheusdingtalk.model;

import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2020-06-22 17:28:29
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private String receiver;
    private String status;
    private List<Alerts> alerts;
    private GroupLabels groupLabels;
    private CommonLabels commonLabels;
    private CommonAnnotations commonAnnotations;
    private Date externalURL;
    private String version;
    private String groupKey;

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setAlerts(List<Alerts> alerts) {
        this.alerts = alerts;
    }

    public List<Alerts> getAlerts() {
        return alerts;
    }

    public void setGroupLabels(GroupLabels groupLabels) {
        this.groupLabels = groupLabels;
    }

    public GroupLabels getGroupLabels() {
        return groupLabels;
    }

    public void setCommonLabels(CommonLabels commonLabels) {
        this.commonLabels = commonLabels;
    }

    public CommonLabels getCommonLabels() {
        return commonLabels;
    }

    public void setCommonAnnotations(CommonAnnotations commonAnnotations) {
        this.commonAnnotations = commonAnnotations;
    }

    public CommonAnnotations getCommonAnnotations() {
        return commonAnnotations;
    }

    public void setExternalURL(Date externalURL) {
        this.externalURL = externalURL;
    }

    public Date getExternalURL() {
        return externalURL;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getGroupKey() {
        return groupKey;
    }

}