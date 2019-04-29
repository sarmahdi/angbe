package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.ToDoItem;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ToDoTestResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")

public class ToDoTestResult  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("input")
  private String input = null;

  @JsonProperty("result")
  private ToDoItem result = null;

  public ToDoTestResult() {
  }

  public ToDoTestResult input(String input) {
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

  public ToDoTestResult result(ToDoItem result) {
    this.result = result;
    return this;
  }

  /**
   * Get result
   * @return result
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ToDoItem getResult() {
    return result;
  }

  public void setResult(ToDoItem result) {
    this.result = result;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ToDoTestResult toDoTestResult = (ToDoTestResult) o;
    return Objects.equals(this.input, toDoTestResult.input) &&
        Objects.equals(this.result, toDoTestResult.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(input, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ToDoTestResult {\n");
    
    sb.append("    input: ").append(toIndentedString(input)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
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

