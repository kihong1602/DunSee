package com.dfo.dunsee.apiResponse.characterList;


import com.dfo.dunsee.apiResponse.ApiResponse;
import java.util.List;
import lombok.Data;

@Data
public class ResponseCharacterList implements ApiResponse {

  private List<ResponseCharacterInfo> rows;

}
