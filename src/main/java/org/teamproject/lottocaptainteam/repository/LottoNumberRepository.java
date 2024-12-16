package org.teamproject.lottocaptainteam.repository;

import java.util.List;
import org.teamproject.lottocaptainteam.domain.LottoTest;

public interface LottoNumberRepository {
    LottoTest add (LottoTest lotto);
    List<LottoTest> findLottoNumbersByMember (String memberId);
    List<LottoTest> findLottoNumbersByDraw(String drawId);
    List<LottoTest> findAll();
}
