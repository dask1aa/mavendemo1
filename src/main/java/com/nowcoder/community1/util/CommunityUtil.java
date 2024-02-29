package com.nowcoder.community1.util;


import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class CommunityUtil {
    // 生成随机字符串
    public static String generateUUID () { // generate: 生成
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    // 用MD5算法加密字符
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
