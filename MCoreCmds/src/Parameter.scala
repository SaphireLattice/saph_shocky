import java.util.StringTokenizer

class Parameter(input:String) {
    def tokens = new StringTokenizer(input)
    def name = this.tokens.nextToken
    def tokenCount = this.tokens.countTokens
}
