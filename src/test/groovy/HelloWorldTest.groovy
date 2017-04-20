/**
 * Created by kambiz on 08/04/2017.
 */



import com.xxx.component.MapSetDomainFunctionFactory
import com.xxx.component.MapSetMetaDataValidator
import com.xxx.component.validation.OrganizationValidator
import com.xxx.domain.Contact
import com.xxx.domain.ContactUnit
import com.xxx.domain.FunctionalUnitInfo
import com.xxx.domain.MapSetMetaData
import com.xxx.domain.Organization
import com.xxx.service.FunctionalUnitService
import com.xxx.util.ValueMapConverter
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import spock.lang.Specification
// Hit 'Run Script' below
class MyFirstSpec extends Specification {
    def "let's try this!"() {
        expect:
        Math.max(1, 2) == 2
    }
    //TODO::must be refactored
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

    def "Commanding to be served the existent toContactFromCwid function succeeds"() {
        given:
        def MapSetDomainFunctionFactory ut

        when:
        def toContactFromCwidFunction = MapSetDomainFunctionFactory.commandToFunctions.apply("toContactFromCwid")

        then:
        toContactFromCwidFunction != null
        Optional<Organization> contactInstance = toContactFromCwidFunction.apply("S")
        contactInstance.isPresent()
    }

    def "Null Contact produces a validation error"() {
        given:
        OrganizationValidator ut = new OrganizationValidator()
        when:
        Contact itemForValidation = null
        ut.validate(itemForValidation, "Contact")
        then:
        ut.getResults().size()==2
        println(ut.getResults().toListString())
    }

    def "Null Contact and null ContactUnit produces a mutually exclusive but mandatory validation error"() {
        given:
        OrganizationValidator ut = new OrganizationValidator()
        when:
        Contact itemForValidation = null
        ContactUnit secondForValidation = null
        ut.validate(itemForValidation, "Contact", secondForValidation, "ContactUnit")
        then:
        ut.getResults().size()==1
        println(ut.getResults().toListString())
    }

    def "Null Contact and not null ContactUnit passes validation"() {
        given:
        OrganizationValidator ut = new OrganizationValidator()
        when:
        Contact itemForValidation = null
        ContactUnit secondForValidation = new ContactUnit()
        ut.validate(itemForValidation, "Contact", secondForValidation, "ContactUnit")
        then:
        ut.getResults().size()==1
        println(ut.getResults().toListString())
    }

    def "Testing thisToThat with mixture of array input and single input succeeds"() {

        given:
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<>();
        Map<String, Object> input = new HashMap<>();
        List<String> inputValues = new ArrayList<>();
        String singleValue = "second"

        inputValues.add("1");
        inputValues.add("2");
        inputValues.add("3");
        inputValues.add("4");
        inputValues.add("5");
        inputValues.add("6");

        input.put("1", inputValues);
        input.put("2", singleValue);
        when:
        ValueMapConverter ut = new ValueMapConverter();
        ut.convertThisToThat(input, mvm);

        then:
        mvm.values().contains(inputValues)
        List<String> mirrorSingleValueInCollection = new ArrayList<>();
        mirrorSingleValueInCollection.add(singleValue)
        mvm.values().contains(mirrorSingleValueInCollection)
        mvm.size() == 2
        print(mvm.toString())
    }

    def "Testing thisToThat with null value"() {

        given:
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<>();
        Map<String, Object> input = new HashMap<>();
        List<String> inputValues = new ArrayList<>();
        String singleValue = null
        input.put("2", singleValue);
        when:
        ValueMapConverter ut = new ValueMapConverter();
        ut.convertThisToThat(input, mvm);

        then:
        mvm.values().contains(inputValues)
        List<String> mirrorSingleValueInCollection = new ArrayList<>();
        mirrorSingleValueInCollection.add(singleValue)
        mvm.values().contains(mirrorSingleValueInCollection)
        mvm.size() == 2
        print(mvm.toString())
    }
}