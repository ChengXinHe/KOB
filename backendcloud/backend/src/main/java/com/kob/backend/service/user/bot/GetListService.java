package com.kob.backend.service.user.bot;

import com.kob.backend.pojo.Bot;

import java.util.List;

public interface GetListService {
    List<Bot> getList();
    //user_id 存在token里，所以不用传参数？？没懂
}
