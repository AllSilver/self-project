package org.zerock.command;

import org.zerock.dto.BDto;
import org.zerock.dto.MDto;

public interface SCommand {

	MDto execute(String userName);

}
