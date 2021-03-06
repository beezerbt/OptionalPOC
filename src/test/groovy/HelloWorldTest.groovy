/**
 * Created by kambiz on 08/04/2017.
 */


import com.funinfo.work.FunctionalUnitService
import com.funinfo.work.MapSetMetaDataValidator
import com.validation.*
import com.validation.domain.MapSetMetaData
import spock.lang.*

// Hit 'Run Script' below
class MyFirstSpec extends Specification {
    def "let's try this!"() {
        expect:
        Math.max(1, 2) == 2
    }

    def "Valid contanctUnitKey results in valid functionalUnitInfo"() {
        given:
        String expectedContactUnitName = "Crop Anal and Crop Geno"
        def expectedFunctionalUnitInfo = new FunctionalUnitInfo("CA-CG", expectedContactUnitName)
        def mockedUnvalidated = Mock(MapSetMetaData)
        mockedUnvalidated.getContactUnitKey() >> "CA-CG"
        def mockedFunctionalUnitService = Mock(FunctionalUnitService)
        mockedFunctionalUnitService.toFunctionalUnitInfo("CA-CG") >> expectedFunctionalUnitInfo
        MapSetMetaDataValidator ut = new MapSetMetaDataValidator(true, mockedUnvalidated, mockedFunctionalUnitService)

        when:
        ut.validate()

        then:
        def actualFunctionalUnitInfo = ut.getFunctionalUnitInfo()
        actualFunctionalUnitInfo.getContactUnitKey() == expectedFunctionalUnitInfo.getContactUnitKey()
        actualFunctionalUnitInfo.getContactUnitNaame() == expectedContactUnitName
    }

    def "No incoming contanctUnitKey means no functionalUnitInfo"() {
        given:
        def expectedFunctionalUnitInfo = new FunctionalUnitInfo("CA-CG", "Null contactUnitKey so this is not every going to be the case")
        def mockedUnvalidated = Mock(MapSetMetaData)
        mockedUnvalidated.getContactUnitKey() >> null
        def mockedFunctionalUnitService = Mock(FunctionalUnitService)
        mockedFunctionalUnitService.toFunctionalUnitInfo("CA-CG") >> expectedFunctionalUnitInfo
        def ut = new MapSetMetaDataValidator(true, mockedUnvalidated, mockedFunctionalUnitService)

        when:
        ut.validate()

        then:
        ut.getFunctionalUnitInfo() == null
    }

    def "InValid contanctUnitKey results in no functionalUnitInfo"() {
        given:
        String validContactUnitName = "Crop Anal and Crop Geno"
        def expectedFunctionalUnitInfo = new FunctionalUnitInfo("CA-CG", validContactUnitName)
        def mockedUnvalidated = Mock(MapSetMetaData)
        mockedUnvalidated.getContactUnitKey() >> "UNRECOGNIZEABLE-UNIT-KEY"
        def mockedFunctionalUnitService = Mock(FunctionalUnitService)
        mockedFunctionalUnitService.toFunctionalUnitInfo("CA-CG") >> expectedFunctionalUnitInfo
        mockedFunctionalUnitService.toFunctionalUnitInfo("UNRECOGNIZEABLE-UNIT-KEY") >> null
        def ut = new MapSetMetaDataValidator(true, mockedUnvalidated, mockedFunctionalUnitService)

        when:
        ut.validate()

        then:
        ut.getFunctionalUnitInfo() == null
    }
}