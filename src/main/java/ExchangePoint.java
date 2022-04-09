import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangePoint {
    private static final int SCALE = 4;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    private final RatesProvider ratesProvider;
    private final BigDecimal askSpread;
    private final BigDecimal bidSpread;

    public ExchangePoint(RatesProvider ratesProvider, BigDecimal spread) {
        this.ratesProvider = ratesProvider;
        this.askSpread = spread;
        this.bidSpread = spread;
        //TODO
    }

    public BigDecimal getBidSpread(BigDecimal amount, Currency from, Currency to){
        return null;
    }

    public BigDecimal getAskSpread(BigDecimal amount, Currency from, Currency to){
        return null;
    }
}
