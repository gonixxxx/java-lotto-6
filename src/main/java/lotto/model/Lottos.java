package lotto.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void showLottos() {
        for (Lotto lotto : lottos) {
            lotto.showLotto();
        }
    }

    public Map<Rank, Integer> saveRankResult(User user) {
        Map<Rank, Integer> rankResult = initRank();
        for (Lotto lotto : lottos) {
            Rank rank = user.getRank(lotto);
            rankResult.put(rank, rankResult.get(rank) + 1);
        }
        return rankResult;
    }

    private Map<Rank, Integer> initRank() {
        Map<Rank, Integer> rankResult = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankResult.put(rank, 0);
        }
        return rankResult;
    }

    public double calEarnings(Map<Rank, Integer> rankResult) {
        double earnings = 0;
        for (Rank rank : Rank.values()) {
            earnings += (rankResult.get(rank) * rank.getPrize());
        }
        return earnings;
    }

}
