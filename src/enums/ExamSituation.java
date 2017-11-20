package enums;

public enum ExamSituation {
	PENDENT("Pendente"),

	FINISH("Finalizada");

	private String value;

	private ExamSituation(String string) {
		this.value = string;
	}

	@Override
	public String toString() {
		return value;
	}
}
