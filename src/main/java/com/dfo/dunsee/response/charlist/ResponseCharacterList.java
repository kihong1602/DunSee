package com.dfo.dunsee.response.charlist;


import com.dfo.dunsee.response.ApiResponse;
import java.util.List;
import lombok.Data;

@Data
public class ResponseCharacterList implements ApiResponse {

  private List<ResponseCharacterInfo> rows;

}
