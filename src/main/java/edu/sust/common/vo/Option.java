package edu.sust.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前端选择器select需要的属性,泛型是value的类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option<T> {
    private String label;
    private T value;
}
