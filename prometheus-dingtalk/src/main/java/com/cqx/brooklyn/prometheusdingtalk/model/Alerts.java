/**
 * Copyright 2020 bejson.com
 */
package com.cqx.brooklyn.prometheusdingtalk.model;

import java.util.Date;

/**
 * Auto-generated: 2020-06-22 17:28:29
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Alerts {

    private String status;
    private Labels labels;
    private Annotations annotations;
    private Date startsAt;
    private Date endsAt;
    private String generatorURL;
    private String fingerprint;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public Labels getLabels() {
        return labels;
    }

    public void setAnnotations(Annotations annotations) {
        this.annotations = annotations;
    }

    public Annotations getAnnotations() {
        return annotations;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setGeneratorURL(String generatorURL) {
        this.generatorURL = generatorURL;
    }

    public String getGeneratorURL() {
        return generatorURL;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFingerprint() {
        return fingerprint;
    }

}