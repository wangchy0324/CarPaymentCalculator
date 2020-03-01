import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class CarPaymentCalculatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testComputePayment() {
		CarPaymentCalculator cp=new CarPaymentCalculator();
		double result=cp.computePayment(10000.0, 0.0, 10, 1000);
		BigDecimal reb = new BigDecimal(result);
		BigDecimal rebtest = new BigDecimal(900.0);
		assertEquals(reb.compareTo(rebtest),0);
		
		double result1=cp.computePayment(10000.0, 0.0, 0, 1000);
		BigDecimal reb1 = new BigDecimal(result1);
		BigDecimal rebtest1 = new BigDecimal(0.0);
		assertEquals(reb1.compareTo(rebtest1),0);
		
		double result2=cp.computePayment(10000.0, 2.5, 0, 1000);
		BigDecimal reb2 = new BigDecimal(result2);
		BigDecimal rebtest2 = new BigDecimal(0.0);
		assertEquals(reb1.compareTo(rebtest1),0);
		
		double result3=cp.computePayment(10000.0, 2.5, 24, 1000);
		BigDecimal reb3 = new BigDecimal(result3);
		BigDecimal rebtest3 = new BigDecimal(384.84);
		assertEquals(reb1.compareTo(rebtest1),0);
	}

}
