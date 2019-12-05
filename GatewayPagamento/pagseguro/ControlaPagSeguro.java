package pagseguro;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.application.authorization.AuthorizationRegistration;
import br.com.uol.pagseguro.api.application.authorization.AuthorizationRegistrationBuilder;
import br.com.uol.pagseguro.api.application.authorization.RegisteredAuthorization;
import br.com.uol.pagseguro.api.common.domain.PermissionCode;
import br.com.uol.pagseguro.api.credential.Credential;

public class ControlaPagSeguro {

	/**
	 * Credenciais PagSeguro
	 * 
	 * correiodolulu@gmail.com
	 * 
	 * token: 7B6A4879E9644A0AB503FB0E54B73502
	 * 
	 * Aplicação de Teste
	 * 
	 * appId: app8602065762
	 * 
	 * AppKey: 1DE4E454EAEA9AF0041E2FB2AE54FF49
	 *
	 * Vendedor de Teste:
	 * 
	 * email: v15798453804267757692@sandbox.pagseguro.com.br
	 * 
	 * Senha: 40kB725320967974
	 * 
	 * Chave de Teste: PUB5CD507288E734DB8A5CFAE8905B8F3E5
	 * 
	 * Comprador de teste:
	 * 
	 * Email: c50460421788986382551@sandbox.pagseguro.com.br +++++++++++++++
	 * Senha: 9633100416T8P186
	 * 
	 * Cartão de Crédito de Teste:
	 * 
	 * 4111111111111111
	 * 
	 * Bandeira: VISA Válido até: 12/2030 CVV: 123
	 * 
	 */

	public ControlaPagSeguro() {
		// TODO Auto-generated constructor stub
	}

	public static void createAuthorization(String appId, String appKey) {

		// valores para teste
		appId = "app8602065762";
		appKey = "1DE4E454EAEA9AF0041E2FB2AE54FF49";

		try {

			final PagSeguro pagSeguro = PagSeguro.instance(
					Credential.applicationCredential(appId, appKey),
					PagSeguroEnv.SANDBOX);

			// Registra as autorizações
			AuthorizationRegistration authorizationRegistration = new AuthorizationRegistrationBuilder()
					.withNotificationUrl("www.lojatesteste.com.br/notification")
					.withReference("REF_001")
					.withRedirectURL("www.lojatesteste.com.br")
					.addPermission(PermissionCode.Code.CREATE_CHECKOUTS)
					.build();

			RegisteredAuthorization ra = pagSeguro.authorizations()
					.register(authorizationRegistration);
			System.out.print(ra);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		createAuthorization(null, null);

	}
}
