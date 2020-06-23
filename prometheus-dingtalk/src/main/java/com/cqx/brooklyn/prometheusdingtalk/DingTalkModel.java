package com.cqx.brooklyn.prometheusdingtalk;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DingTalkModel {

    /**
     * {
     * "msgtype": "markdown",
     * "markdown": {
     * "title":"杭州天气",
     * "text": "#### 杭州天气 @150XXXXXXXX \n> 9度，西北风1级，空气良89，相对温度73%\n> ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n> ###### 10点20分发布 [天气](https://www.dingalk.com) \n"
     * },
     * "at": {
     * "atMobiles": [
     * "150XXXXXXXX"
     * ],
     * "isAtAll": false
     * }
     * }
     */

    private String msgtype = "markdown";
    private Markdown markdown;
    private At at;

    @Getter
    @Setter
    public static class Markdown {
        private String title;
        private String text;
    }

    @Getter
    @Setter
    public static class At {
        private List<String> atMobiles;
        private Boolean isAtAll;
    }
}
