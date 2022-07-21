import kotlin.math.max
import kotlin.random.Random

enum class CardType {
    VKPAY, MASTERCARD, MAESTRO, OTHER, VISA, MIR
}

fun main() {
    //val amount:Int = 5_000_50
    val amount:Int = Random.nextInt(0, 5_000_00)
    val previousTransfersSum:Int = 10_000_00

    val commission:Int = getCommission(CardType.MAESTRO, previousTransfersSum, amount)

    println("Сумма списания: ${amount/100.0} руб., комиссия ${commission/100.0} руб., получателю придет ${(amount-commission)/100.0} руб.")
}

fun getCommission(cardType:CardType=CardType.VKPAY, previousTransfersSum:Int=0, amount:Int):Int {
    return when (cardType) {
        CardType.VKPAY -> 0

        CardType.MASTERCARD, CardType.MAESTRO -> if ((amount+previousTransfersSum)>300_00 && (amount+previousTransfersSum)<75_000_00) {0} else {20_00 + amount/1000*6}

        CardType.VISA, CardType.MIR, CardType.OTHER  -> max(35_00, amount * 75 / 10000)
    }
}