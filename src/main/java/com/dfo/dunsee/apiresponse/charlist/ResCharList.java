package com.dfo.dunsee.apiresponse.charlist;


import com.dfo.dunsee.apiresponse.ApiResponse;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResCharList implements ApiResponse {

  private List<ResCharInfo> rows;

}
