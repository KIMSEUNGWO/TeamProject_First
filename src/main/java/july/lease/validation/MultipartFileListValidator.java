package july.lease.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileListValidator implements ConstraintValidator<ValidFiles, List<MultipartFile>>{

	private int min;
    private int max;

    @Override
    public void initialize(ValidFiles constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(List<MultipartFile> files, ConstraintValidatorContext context) {
        if (files == null) {
            return false;
        }

        long validFileCount = files.stream().filter(file -> !file.isEmpty()).count();

        return validFileCount >= min && validFileCount <= max;
    }
}
