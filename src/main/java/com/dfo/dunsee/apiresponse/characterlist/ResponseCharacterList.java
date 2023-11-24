package com.dfo.dunsee.apiresponse.characterlist;


import com.dfo.dunsee.apiresponse.ApiResponse;
import java.util.List;
import lombok.Data;

@Data
public class ResponseCharacterList implements ApiResponse {

  private List<ResponseCharacterInfo> rows;

}
