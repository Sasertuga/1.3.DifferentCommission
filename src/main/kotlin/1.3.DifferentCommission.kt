const val MAX_TRANSFER_IN_DAY = 150_000
const val MAX_TRANSFER_VK_PAY = 15_000

const val MASTERCARD = "MasterCard"
const val MAESTRO = "Maestro"
const val VISA = "Visa"
const val MIR = "Mir"
const val VK_PAY = "VK Pay"

fun main() {
    commissionForTransfer(VK_PAY, 15000)
    commissionForTransfer(MASTERCARD, 7500)
    commissionForTransfer(MIR, 40)
}

fun commissionForTransfer(typeCard: String, sumTransfer: Int) {

    val sum = if (sumTransfer != 0 && sumTransfer > 0) sumTransfer * 100 else return

    val commissionPayment: Double = when (typeCard) {
        MASTERCARD, MAESTRO -> 0.6
        VISA, MIR -> 0.75
        else -> 0.0
    }

    val transfer = (sum * commissionPayment / 100).toInt()

    val verification = if (sum <= MAX_TRANSFER_IN_DAY * 100) {
        when (typeCard) {
            MASTERCARD, MAESTRO -> transfer + 2000
            VISA, MIR -> if (transfer < 35) 3500 else transfer
            VK_PAY -> if (sum <= MAX_TRANSFER_VK_PAY * 100) transfer else error("Не больше 15 000 рублей за раз")
            else -> 0
        }
    } else error("Превышен максимальный лимит в 150 000 рублей")


    println("Сумма перевода: $sum копеек. Комиссия за перевод по $typeCard : $verification копеек")
}


