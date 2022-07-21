import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun getCommission_AllForMastercard() {
        val cardType:CardType = CardType.MASTERCARD
        var previousTransfersSum:Int = 75_001_00
        var amount:Int = 3_430_29

        // big previousTransfersSum :
        var commission:Int = getCommission(cardType, previousTransfersSum, amount)
        assertEquals(40_58, commission)

        // small previousTransfersSum :
        previousTransfersSum = 0
        amount = 250_00
        commission = getCommission(cardType, previousTransfersSum, amount)
        assertEquals(21_50, commission)

        // middle previousTransfersSum :
        previousTransfersSum = 10_000_00
        amount = 300_00
        commission = getCommission(cardType, previousTransfersSum, amount)
        assertEquals(0, commission)
    }

    @Test
    fun getCommission_AllForVKPAY() {
        val cardType:CardType = CardType.VKPAY
        val previousTransfersSum:Int = 75_001_00
        val amount:Int = 3_430_29

        val commission:Int = getCommission(cardType, previousTransfersSum, amount)
        assertEquals(0, commission)
    }

    @Test
    fun getCommission_AllForOTHER() {
        val cardType:CardType = CardType.OTHER
        val previousTransfersSum:Int = 75_001_00
        var amount:Int = 3_430_29

        // small amount
        var commission:Int = getCommission(cardType, previousTransfersSum, amount)
        assertEquals(35_00, commission)

        // big amount
        amount = 30_000_00
        commission = getCommission(cardType, previousTransfersSum, amount)
        assertEquals(225_00, commission)
    }

}