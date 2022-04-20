import org.junit.Test
import org.junit.Assert.*

class DifferentCommissionKtTest {

    @Test
    fun commissionCalculate_if() {
        val card = MASTERCARD
        val sum = 1000

        val result = commissionCalculate(sumTransfer = sum, typeCard = card)

        assertEquals(6.0, result, 0.0)
    }


    @Test
    fun commissionForTransfer_Mastercard_and_Maestro() {
        val card = MASTERCARD
        val transferForMonth = 0
        val sum = 100

        val result = commissionForTransfer(typeCard = card, allMonth = transferForMonth, sumTransfer = sum)

        assertEquals(2060, result)
    }

    @Test
    fun commissionForTransfer_Visa_and_Mir_if() {
        val card = VISA
        val transferForMonth = 0
        val sum = 1000

        val result = commissionForTransfer(typeCard = card, allMonth = transferForMonth, sumTransfer = sum)

        assertEquals(35, result)
    }

    @Test
    fun commissionForTransfer_Visa_and_Mir_else() {
        val card = MIR
        val transferForMonth = 0
        val sum = 34

        val result = commissionForTransfer(typeCard = card, allMonth = transferForMonth, sumTransfer = sum)

        assertEquals(35, result)
    }

    @Test
    fun commissionForTransfer_VK_Pay_if() {
        val card = VK_PAY
        val transferForMonth = 0
        val sum = 1000

        val result = commissionForTransfer(typeCard = card, allMonth = transferForMonth, sumTransfer = sum)

        assertEquals(0, result)
    }

    @Test
    fun commissionForTransfer() {
        val card = VISA
        val transferForMonth = 0
        val sum = 15000

        val result = commissionForTransfer(typeCard = card, allMonth = transferForMonth, sumTransfer = sum)

        assertEquals(11250, result)
    }

    @Test
    fun transfersForThisMonth_for_Mastercard_Maestro_Visa_Mir() {
        val card = VISA
        val transferForMonth = 0

        val result = transfersForThisMonth(allMonth = transferForMonth, typeCard = card)

        assertEquals(600_000, result)
    }

    @Test
    fun transfersForThisMonth_for_VK_Pay() {
        val card = VK_PAY
        val transferForMonth = 1000

        val result = transfersForThisMonth(allMonth = transferForMonth, typeCard = card)

        assertEquals(39000, result)
    }

    @Test
    fun commissionForTransfer_else() {
        val card = "unknown"
        val transferForMonth = 0
        val sum = 100

        val result = commissionForTransfer(typeCard = card, allMonth = transferForMonth, sumTransfer = sum)

        assertEquals(0, result)
    }
}