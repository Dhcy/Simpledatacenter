package com.good.plat.datacenter.datastat.entity.analysis;

public class LogplatWarningNoticeWithBLOBs extends LogplatWarningNotice {
    private String content;

    private String receiver;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }
}