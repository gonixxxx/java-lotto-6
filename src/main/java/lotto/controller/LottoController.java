package lotto.controller;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.User;
import lotto.utils.InputValidator;
import lotto.utils.LottoGenerator;
import lotto.utils.StringConvertor;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Money money = getMoney();
        Lottos lottos = makeLotto(money);
        outputView.showLottos(lottos, money);

        Lotto userLotto = getLotto();
        User user = getBonus(userLotto);
        outputView.showLottoStatistics(lottos, user, money);
    }

    private Money getMoney() {
        try {
            return new Money(inputView.inputPrice());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMoney();
        }
    }

    private Lotto getLotto() {
        try {
            String inputLotto = inputView.inputLottoNumber();
            InputValidator.checkLottoNumberFormat(inputLotto);
            List<Integer> userLotto = StringConvertor.stringToList(inputLotto);
            return new Lotto(userLotto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getLotto();
        }
    }

    private User getBonus(Lotto userLotto) {
        try {
            String inputBonus = inputView.inputBonusNumber();
            userLotto.validateBonusNumber(inputBonus);
            return new User(userLotto, inputBonus);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonus(userLotto);
        }
    }

    private Lottos makeLotto(Money money) {
        return new Lottos(LottoGenerator.generateLottos(money.calNumberOfLotto()));
    }

}
