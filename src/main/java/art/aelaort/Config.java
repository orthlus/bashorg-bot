package art.aelaort;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

import static art.aelaort.TelegramBots.createTelegramInit;

@Configuration
public class Config {
	@Bean
	public TelegramClient bashTelegramClient(@Value("${bash.telegram.bot.token}") String token) {
		return TelegramClientBuilder.builder()
				.token(token)
				.build();
	}

	@Bean
	public S3Params bashS3Params(
			@Value("${bash.s3.url}") String url,
			@Value("${bash.s3.region}") String region,
			@Value("${bash.s3.id}") String id,
			@Value("${bash.s3.key}") String key
	) {
		return new DefaultS3Params(id, key, url, region);
	}

	@Bean
	public TelegramInit telegramInit(List<SpringLongPollingBot> bots) {
		return createTelegramInit(bots);
	}
}
