package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * IntegrationTestResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")

public class IntegrationTestResult  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("bracers")
  @Valid
  private List<BracersTestResult> bracers = null;

  @JsonProperty("todo")
  @Valid
  private List<ToDoTestResult> todo = null;

  @JsonProperty("isCorrect")
  private Boolean isCorrect = null;

  public IntegrationTestResult bracers(List<BracersTestResult> bracers) {
    this.bracers = bracers;
    return this;
  }

  public IntegrationTestResult addBracersItem(BracersTestResult bracersItem) {
    if (this.bracers == null) {
      this.bracers = new ArrayList<>();
    }
    this.bracers.add(bracersItem);
    return this;
  }

  /**
   * Get bracers
   * @return bracers
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<BracersTestResult> getBracers() {
    return bracers;
  }

  public void setBracers(List<BracersTestResult> bracers) {
    this.bracers = bracers;
  }

  public IntegrationTestResult todo(List<ToDoTestResult> todo) {
    this.todo = todo;
    return this;
  }

  public IntegrationTestResult addTodoItem(ToDoTestResult todoItem) {
    if (this.todo == null) {
      this.todo = new ArrayList<>();
    }
    this.todo.add(todoItem);
    return this;
  }

  /**
   * Get todo
   * @return todo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ToDoTestResult> getTodo() {
    return todo;
  }

  public void setTodo(List<ToDoTestResult> todo) {
    this.todo = todo;
  }

  public IntegrationTestResult isCorrect(Boolean isCorrect) {
    this.isCorrect = isCorrect;
    return this;
  }

  /**
   * Get isCorrect
   * @return isCorrect
  **/
  @ApiModelProperty(example = "true", value = "")


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
    IntegrationTestResult integrationTestResult = (IntegrationTestResult) o;
    return Objects.equals(this.bracers, integrationTestResult.bracers) &&
        Objects.equals(this.todo, integrationTestResult.todo) &&
        Objects.equals(this.isCorrect, integrationTestResult.isCorrect);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bracers, todo, isCorrect);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntegrationTestResult {\n");
    
    sb.append("    bracers: ").append(toIndentedString(bracers)).append("\n");
    sb.append("    todo: ").append(toIndentedString(todo)).append("\n");
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

