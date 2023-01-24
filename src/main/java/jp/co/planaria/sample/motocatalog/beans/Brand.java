package jp.co.planaria.sample.motocatalog.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ブランド
 */
// Lombokを使用
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
  // ブランドID
  private String brandId;
  // ブランド名
  private String brandName;
}
