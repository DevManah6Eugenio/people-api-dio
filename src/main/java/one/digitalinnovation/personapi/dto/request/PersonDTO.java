package one.digitalinnovation.personapi.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    @ApiModelProperty(value = "Código Pessoa")
    private Long id;

    @ApiModelProperty(value = "Primeiro Nome")
    @NotEmpty
    @Size(min = 2, max = 100)
    private String firstName;

    @ApiModelProperty(value = "Sobrenome")
    @NotEmpty
    @Size(min = 2, max = 100)
    private String lastName;

    @ApiModelProperty(value = "CPF com '.' e '-'")
    @NotEmpty
    @CPF
    private String cpf;

    @ApiModelProperty(value = "Data de nascimento 'dd-MM-yyyy'")
    private String birthDate;

    @ApiModelProperty(value = "lista de telefones")
    @Valid
    @NotEmpty
    private List<PhoneDTO> phones;
}
