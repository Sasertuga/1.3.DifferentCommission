import org.junit.Test
import org.junit.Assert.*

class DifferentCommissionKtTest {

    @Test
    fun commissionCalculate_if() {
        val sum = 1000
        val card = COMMISSION_MASTERCARD_AND_MAESTRO

        val result = commissionCalculate(sumTransfer = sum, typeCard = card)

        assertEquals(600, result)
    }


    @Test
    fun commissionCalculate_else() {
        val sum = 0
        val card = COMMISSION_MASTERCARD_AND_MAESTRO

        val result = commissionCalculate(sumTransfer = sum, typeCard = card)

        assertEquals(0, result)
    }

    @Test
    fun commissionForTransfer_Mastercard_and_Maestro() {
        val sum = 1000
        val card = COMMISSION_MASTERCARD_AND_MAESTRO

        val result = commissionForTransfer(sumTransfer = sum, typeCard = card)

        assertEquals(2600, result)
    }

    @Test
    fun commissionForTransfer_Visa_and_Mir_if() {
        val sum = 1000
        val card = COMMISSION_VISA_AND_MIR

        val result = commissionForTransfer(sumTransfer = sum, typeCard = card)

        assertEquals(750, result)
    }

    @Test
    fun commissionForTransfer_Visa_and_Mir_else() {
        val sum = 34
        val card = COMMISSION_VISA_AND_MIR

        val result = commissionForTransfer(sumTransfer = sum, typeCard = card)

        assertEquals(3500, result)
    }

    @Test
    fun commissionForTransfer_VK_Pay_if() {
        val sum = 1000
        val card = COMMISSION_VK_PAY

        val result = commissionForTransfer(sumTransfer = sum, typeCard = card)

        assertEquals(0, result)
    }

    @Test
    fun commissionForTransfer() {
        val sum = 15000
        val card = 0.1

        val result = commissionForTransfer(sumTransfer = sum, typeCard = card)

        assertEquals(0, result)
    }
}