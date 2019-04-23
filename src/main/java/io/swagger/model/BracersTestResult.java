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
 * BracersTestResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")

public class BracersTestResult  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("input")
  private String input = null;

  @JsonProperty("result")
  private Boolean result = null;

  @JsonProperty("expected")
  private Boolean expected = null;

  @JsonProperty("isCorrect")
  private Boolean isCorrect = null;

  public BracersTestResult input(String input) {
    this.input = input;
    return this;
  }

  /**
   * Get input
   * @return input
  **/
  @ApiModelProperty(value = "")


  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public BracersTestResult result(Boolean result) {
    this.result = result;
    return this;
  }

  /**
   * Get result
   * @return result
  **/
  @ApiModelProperty(value = "")


  public Boolean isResult() {
    return result;
  }

  public void setResult(Boolean result) {
    this.result = result;
  }

  public BracersTestResult expected(Boolean expected) {
    this.expected = expected;
    return this;
  }

  /**
   * Get expected
   * @return expected
  **/
  @ApiModelProperty(value = "")


  public Boolean isExpected() {
    return expected;
  }

  public void setExpected(Boolean expected) {
    this.expected = expected;
  }

  public BracersTestResult isCorrect(Boolean isCorrect) {
    this.isCorrect = isCorrect;
    return this;
  }

  /**
   * Get isCorrect
   * @return isCorrect
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsCorrect() {
    return isCorrect;
  }

  public void setIsCorrect(Boolean isCorrect) {
    this.isCorrect = isCorrect;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BracersTestResult bracersTestResult = (BracersTestResult) o;
    return Objects.equals(this.input, bracersTestResult.input) &&
        Objects.equals(this.result, bracersTestResult.result) &&
        Objects.equals(this.expected, bracersTestResult.expected) &&
        Objects.equals(this.isCorrect, bracersTestResult.isCorrect);
  }

  @Override
  public int hashCode() {
    return Objects.hash(input, result, expected, isCorrect);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BracersTestResult {\n");
    
    sb.append("    input: ").append(toIndentedString(input)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    expected: ").append(toIndentedString(expected)).append("\n");
    sb.append("    isCorrect: ").append(toIndentedString(isCorrect)).append("\n");
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

