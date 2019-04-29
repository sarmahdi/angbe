package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BalanceTestResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")

public class BalanceTestResult extends ToDoResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("input")
  private String input = null;

  @JsonProperty("isBalanced")
  private Boolean isBalanced = null;

  public BalanceTestResult input(String input) {
    this.input = input;
    return this;
  }

  /**
   * Get input
   * @return input
  **/
  @ApiModelProperty(example = "[(]", value = "")


  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public BalanceTestResult isBalanced(Boolean isBalanced) {
    this.isBalanced = isBalanced;
    return this;
  }

  /**
   * Get isBalanced
   * @return isBalanced
  **/
  @ApiModelProperty(example = "false", value = "")


  public Boolean isIsBalanced() {
    return isBalanced;
  }

  public void setIsBalanced(Boolean isBalanced) {
    this.isBalanced = isBalanced;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BalanceTestResult balanceTestResult = (BalanceTestResult) o;
    return Objects.equals(this.input, balanceTestResult.input) &&
        Objects.equals(this.isBalanced, balanceTestResult.isBalanced);
  }

  @Override
  public int hashCode() {
    return Objects.hash(input, isBalanced);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BalanceTestResult {\n");
    
    sb.append("    input: ").append(toIndentedString(input)).append("\n");
    sb.append("    isBalanced: ").append(toIndentedString(isBalanced)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

