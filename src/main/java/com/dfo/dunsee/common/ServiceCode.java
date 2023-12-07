package com.dfo.dunsee.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceCode {
  COM100("홈페이지 이동"),
  MBR101("일반 회원가입"),
  MBR102("OAuth2 회원가입"),
  MBR201("일반 로그인"),
  MBR202("OAuth2 로그인"),
  MBR301("로그아웃"),
  MBR401("즐겨찾기 지정"),
  MBR402("즐겨찾기 조회"),
  MBR403("즐겨찾기 삭제"),
  CHR101("캐릭터 검색 API호출"),
  CHR102("캐릭터 검색 응답데이터 가공"),
  CHR201("캐릭터 상세정보 API호출"),
  CHR202("캐릭터 상세정보 응답데이터 가공"),
  CHR301("모험단 조회 API호출"),
  CHR302("모험단 조회 응답데이터 가공"),
  BRD101("게시판 접속"),
  BRD201("게시글 작성"),
  BRD202("게시글 조회"),
  BRD203("게시글 수정"),
  BRD204("게시글 삭제"),
  BRD205("게시글 추천"),
  BRD301("댓글 작성"),
  BRD302("댓글 수정"),
  BRD303("댓글 삭제"),
  BRD304("댓글 추천"),
  BRD401("게시판 페이징");

  private final String description;

  public static String setServiceMsg(ServiceCode serviceCode) {
    return serviceCode.name() + " | " + serviceCode.getDescription() + " :: ";
  }
}
