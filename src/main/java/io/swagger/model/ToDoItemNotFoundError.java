package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.ToDoItemNotFoundErrorDetails;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ToDoItemNotFoundError
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")

public class ToDoItemNotFoundError extends ToDoResponse  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("details")
  @Valid
  private List<ToDoItemNotFoundErrorDetails> details = null;

  @JsonProperty("name")
  private String name = null;

  public ToDoItemNotFoundError(String notFoundError, Long id) {
    details= new ArrayList<>();
    details.add(new ToDoItemNotFoundErrorDetails(id));
    this.name= notFoundError;
  }

  @JsonCreator
  public ToDoItemNotFoundError(@JsonProperty("details") @Valid List<ToDoItemNotFoundErrorDetails> details, @JsonProperty("name") String name) {
    this.details = details;
    this.name = name;
  }

  public ToDoItemNotFoundError details(List<ToDoItemNotFoundErrorDetails> details) {
    this.details = details;
    return this;
  }

  public ToDoItemNotFoundError addDetailsItem(ToDoItemNotFoundErrorDetails detailsItem) {
    if (this.details == null) {
      this.details = new ArrayList<>();
    }
    this.details.add(detailsItem);
    return this;
  }

  /**
   * Get details
   * @return details
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<ToDoItemNotFoundErrorDetails> getDetails() {
    return details;
  }

  public void setDetails(List<ToDoItemNotFoundErrorDetails> details) {
    this.details = details;
  }

//  public String getFirstMessage(){
//    return this.details.get(0).getMessage();
//  }

  public ToDoItemNotFoundError name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "NotFoundError", value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ToDoItemNotFoundError toDoItemNotFoundError = (ToDoItemNotFoundError) o;
    return Objects.equals(this.details, toDoItemNotFoundError.details) &&
        Objects.equals(this.name, toDoItemNotFoundError.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(details, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ToDoItemNotFoundError {\n");
    
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

