package com.cysion.baselib.net;

import java.util.Map;
//注入通用header

public interface AInjector {
    Map<String, String> headers();
}
