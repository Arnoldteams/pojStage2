package com.cskaoyan.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 曾杨
 * @createTime: 2022年06月25日 21:39:31
 * @Description: 新增问答
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketIssueCreateBO {
    String answer;
    String question;
}
