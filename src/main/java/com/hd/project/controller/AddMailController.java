package com.hd.project.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hd.project.service.MailRecordService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hd.project.batch.SaveMailProcessor;
import com.hd.project.dto.MailRecordDTO;
import com.hd.project.model.MailRecord;

@Controller
public class AddMailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddMailController.class);

	private static final String REDIRECT_PAGE = "redirect:/epostaIslemleri";
	private static final String REDIRECT_ATTR_NAME = "message";

	private final MailRecordService mailRecordService;

	public AddMailController(MailRecordService mailRecordService) {
		this.mailRecordService = mailRecordService;
	}

	@GetMapping("/epostaIslemleri")
	public String getAddMail(Model model) {
		model.addAttribute("mailRecords", mailRecordService.findAll());
		model.addAttribute("mailRecord", new MailRecordDTO());
		return "add_mail";
	}

	@PostMapping("/epostaEkle")
	public String postAddMail(@ModelAttribute("mailRecord") MailRecordDTO mailRecordDTO,
			RedirectAttributes redirectAttributes) {

		try {
			MailRecord mailRecord = new MailRecord();
			mailRecord.setMail(mailRecordDTO.getMail());
			mailRecord.setActive(mailRecordDTO.isActive());

			mailRecordService.save(mailRecord);

			LOGGER.info("E-posta adresi basariyla eklendi : {}", mailRecordDTO.getMail());
			redirectAttributes.addFlashAttribute(REDIRECT_ATTR_NAME, "Success");
		} catch (Exception e) {
			LOGGER.error("E-posta adresi eklenirken bir hata olustu : {}", mailRecordDTO.getMail());
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(REDIRECT_ATTR_NAME, "Failed");
		}

		return REDIRECT_PAGE;
	}

	@PostMapping("/excelEpostaEkle")
	public String postExcelAddMail(@RequestParam("file") MultipartFile customFile,
			RedirectAttributes redirectAttributes) {

		try (Workbook workbook = new XSSFWorkbook(customFile.getInputStream())) {

			ExecutorService executorService = Executors.newFixedThreadPool(4);

			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				for (Cell cell : row) {
					String cellValue = cell.getRichStringCellValue().getString();
					if (cellValue.contains("@")) {
						SaveMailProcessor saveMailProcessor = new SaveMailProcessor(mailRecordService, cellValue);

						executorService.submit(saveMailProcessor);
					}
				}
			}

			executorService.shutdown();

			LOGGER.info("E-posta adresleri basariyla eklendi");
			redirectAttributes.addFlashAttribute(REDIRECT_ATTR_NAME, "Success");
		} catch (Exception e) {
			LOGGER.info("E-posta adresleri ekleniken bir hata olustu");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(REDIRECT_ATTR_NAME, "Failed");
		}

		return REDIRECT_PAGE;
	}

}
