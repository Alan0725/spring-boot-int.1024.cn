package cn.int1024.cat.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description:
 * @Author: 双料特工 · 钏疝钾
 * @Date: 2022/10/18 22:20
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    private Integer id;

    private String name;

    private String value;
}
