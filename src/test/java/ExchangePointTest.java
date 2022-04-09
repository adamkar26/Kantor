import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExchangePointTest {

    private FixedRatesProvider fixedRatesProvider;
    @BeforeEach
    void setUp() {
        fixedRatesProvider = new FixedRatesProvider();
        fixedRatesProvider.setRate(Currency.PLN, Currency.EUR, new BigDecimal("4.7"));
        fixedRatesProvider.setRate(Currency.PLN, Currency.USD, new BigDecimal("4.0"));
    }

    @Test
    void shouldntAcceptNegativeSpread(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExchangePoint(fixedRatesProvider, new BigDecimal("-1")) );
    }

    @Test
    void shouldntAcceptSpreadEqualTo1(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExchangePoint(fixedRatesProvider, BigDecimal.ONE) );
    }

    void shouldntAcceptSpreadBiggerThan1(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExchangePoint(fixedRatesProvider, BigDecimal.TEN) );
    }

    @Test
    void shouldReturnBidPriceForCurrencyAfterSpread(){
        ExchangePoint exchangePoint = new ExchangePoint(fixedRatesProvider, new BigDecimal("0.5"));
        assertEquals(exchangePoint.getBidSpread(new BigDecimal("10"), Currency.PLN, Currency.EUR),
                new BigDecimal("2.02"));
    }

    @Test
    void shouldReturnAskPriceForCurrencyAfterSpread(){
        ExchangePoint exchangePoint = new ExchangePoint(fixedRatesProvider, new BigDecimal("0.5"));
        assertEquals(exchangePoint.getBidSpread(new BigDecimal("10"), Currency.EUR, Currency.PLN),
                new BigDecimal("44.65"));
    }


}