const val MAX_TRANSFER_IN_DAY = 150_000
const val MAX_TRANSFER_VK_PAY = 15_000

const val MASTERCARD = "MasterCard"
const val MAESTRO = "Maestro"
const val VISA = "Visa"
const val MIR = "Mir"
const val VK_PAY = "VK Pay"

const val COMMISSION_MASTERCARD_AND_MAESTRO = 0.6
const val COMMISSION_VISA_AND_MIR = 0.75
const val COMMISSION_VK_PAY = 0.0

fun main() {
    println("Сумма перевода ${printSumTransfer(100)} рублей. Комиссия за перевод по карте $MAESTRO : ${commissionForTransfer(100, COMMISSION_MASTERCARD_AND_MAESTRO)} копеек")
}

fun printSumTransfer(sumTransfer: Int): Int {
   return sumTransfer
}

fun commissionCalculate(sumTransfer: Int, typeCard: Double): Int {
    val sum = if (sumTransfer != 0 && sumTransfer > 0) sumTransfer * 100 else 0
    return (sum * typeCard / 100).toInt()
}

fun commissionForTransfer(sumTransfer: Int, typeCard: Double): Number {
    if (sumTransfer * 100 <= MAX_TRANSFER_IN_DAY * 100) {
        return when (typeCard) {
            COMMISSION_MASTERCARD_AND_MAESTRO -> commissionCalculate(sumTransfer, typeCard) + 2000
            COMMISSION_VISA_AND_MIR -> if (commissionCalculate(sumTransfer, typeCard) > 35) commissionCalculate(
                sumTransfer,
                typeCard
            ) else 3500
            COMMISSION_VK_PAY -> if (sumTransfer * 100 <= MAX_TRANSFER_VK_PAY * 100) commissionCalculate(
                sumTransfer,
                typeCard
            ) else error("Не больше 15 000 рублей за раз")
            else -> error("неправильный тип карты")
        }
    } else error("Превышен суточный лимит")
}




