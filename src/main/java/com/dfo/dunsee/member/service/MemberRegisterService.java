package com.dfo.dunsee.member.service;

import static com.dfo.dunsee.common.ResultType.FAILURE;
import static com.dfo.dunsee.common.ResultType.SUCCESS;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.member.dto.JoinMemberInfo;
import com.dfo.dunsee.member.dto.UsernameDto;
import com.dfo.dunsee.member.entity.Member;
import com.dfo.dunsee.member.repository.MemberRepository;
import com.dfo.dunsee.member.utils.MemberUtils;
import jakarta.transaction.Transactional;
import jakarta.transaction.TransactionalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberRegisterService {

  private final MemberRepository memberRepository;
  private final MemberUtils memberUtils;

  @Transactional
  public ResultType memberRegister(ServiceCode serviceCode, JoinMemberInfo joinMemberInfo) {

    Member saveMember = memberUtils.joinMemberInfoToMember(joinMemberInfo);

    ResultType duplicationCheck = isDuplicateMember(saveMember);

    return saveMemberProcess(serviceCode, duplicationCheck, saveMember);
  }

  private ResultType isDuplicateMember(Member saveMember) {
    UsernameDto existingUsername = memberRepository.findUsernameByEmail(saveMember.getEmail());

    return existingUsername == null ? SUCCESS : FAILURE;
  }


  private ResultType saveMemberProcess(ServiceCode serviceCode, ResultType resultType, Member saveMember) {
    return switch (resultType) {

      case SUCCESS -> {
        try {
          memberRepository.save(saveMember);
          yield SUCCESS;
        } catch (DataAccessException | TransactionalException e) {
          log.error(serviceCode + " | Error", e);
          yield FAILURE;
        }
      }

      case FAILURE -> FAILURE;

    };
  }


}
