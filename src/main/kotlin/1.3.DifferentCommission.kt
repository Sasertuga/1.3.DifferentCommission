const val MAX_TRANSFER_IN_DAY = 150_000
const val MAX_TRANSFER_IN_DAY_FOR_VK_PAY = 15_000
const val MAX_TRANSFER_IN_MONTH = 600_000
const val MAX_TRANSFER_IN_MONTH_VK_PAY = 40_000
const val MIN_COMMISSION_MASTERCARD_AND_MAESTRO = 20
const val MIN_COMMISSION_VISA_AND_MIR = 35

const val MASTERCARD = "MasterCard"
const val MAESTRO = "Maestro"
const val VISA = "Visa"
const val MIR = "Mir"
const val VK_PAY = "VK_Pay"

fun main() {
    println("Сумма перевода ${printSumTransfer(1000)} рублей. Комиссия по карте: $VK_PAY ${commissionForTransfer(VK_PAY, 0, 1000)} копеек")
}

fun discountForCard(typeCard: String): Double {
    return when (typeCard) {
        MASTERCARD, MAESTRO -> 0.6
        VISA, MIR -> 0.75
        else -> 0.0
    }
}

fun transfersForThisMonth(allMonth: Int, typeCard: String): Int {
    return when {
        typeCard != VK_PAY && allMonth < MAX_TRANSFER_IN_MONTH -> MAX_TRANSFER_IN_MONTH - allMonth
        typeCard != VK_PAY && allMonth > MAX_TRANSFER_IN_MONTH -> error("Превышен лимит за месяц по вашей карте")
        typeCard == VK_PAY && allMonth < MAX_TRANSFER_IN_MONTH_VK_PAY -> MAX_TRANSFER_IN_MONTH_VK_PAY - allMonth
        else -> error("Превышен лимит за месяц по вашей карте")
    }
}

fun commissionCalculate(sumTransfer: Int, typeCard: String): Double {
    return if (sumTransfer != 0 && sumTransfer > 0) sumTransfer * discountForCard(typeCard) / 100 else error("Не может быть ноль или меньше нуля")
}

fun commissionForTransfer(typeCard: String, allMonth: Int, sumTransfer: Int): Number {
    return if (sumTransfer <= transfersForThisMonth(allMonth = allMonth, typeCard = typeCard)) {
        if (sumTransfer <= MAX_TRANSFER_IN_DAY) {
            when (typeCard) {
                MASTERCARD, MAESTRO -> ((commissionCalculate(
                    sumTransfer,
                    typeCard
                ) + MIN_COMMISSION_MASTERCARD_AND_MAESTRO) * 100).toInt()
                VISA, MIR -> if (commissionCalculate(
                        sumTransfer,
                        typeCard
                    ) > MIN_COMMISSION_VISA_AND_MIR
                ) (commissionCalculate(
                    sumTransfer,
                    typeCard
                ) * 100).toInt() else MIN_COMMISSION_VISA_AND_MIR
                VK_PAY -> if (sumTransfer <= MAX_TRANSFER_IN_DAY_FOR_VK_PAY) (commissionCalculate(
                    sumTransfer,
                    typeCard
                ) * 100).toInt() else error("Не больше 15 000 рублей за раз")
                else -> 0
            }
        } else error("Превышен суточный лимит")
    } else error("Превышен лимит за месяц по вашей карте")
}

fun printSumTransfer(sumTransfer: Int): String {
   return "$sumTransfer"
}


