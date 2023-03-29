package lesson04.task03;

public enum Country {
    PL {
        @Override
        public String toString() {
            return "Polska";
        }
    }, NL {
        @Override
        public String toString() {
            return "Nederland";
        }
    }, DE {
        @Override
        public String toString() {
            return "Deutschland";
        }
    }
}
