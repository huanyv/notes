
const baseUtil = {
	base58: {
		encode: (input) => baseX('123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz').encode(new TextEncoder().encode(input)),
		decode: (input) => new TextDecoder().decode(baseX('123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz').decode(input)),
	},
	base62: {
		encode: (input) => baseX('0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz').encode(new TextEncoder().encode(input)),
		decode: (input) => new TextDecoder().decode(baseX('0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz').decode(input)),
	}
};

// 引入 BaseX 库（Base58 和 Base62）
function baseX(alphabet) {
	const baseMap = new Uint8Array(256);
	for (let j = 0; j < baseMap.length; j++) baseMap[j] = 255;
	for (let i = 0; i < alphabet.length; i++) baseMap[alphabet.charCodeAt(i)] = i;

	const base = alphabet.length;
	const leader = alphabet.charAt(0);

	return {
		encode(source) {
			if (!source.length) return '';
			let zeros = 0;
			let length = 0;
			let pbegin = 0;
			const pend = source.length;

			while (pbegin !== pend && source[pbegin] === 0) {
				pbegin++;
				zeros++;
			}

			const size = ((pend - pbegin) * (Math.log(256) / Math.log(base)) + 1) >>> 0;
			const b58 = new Uint8Array(size);

			while (pbegin !== pend) {
				let carry = source[pbegin];
				let i = 0;
				for (let it = size - 1; (carry !== 0 || i < length) && it !== -1; it--, i++) {
					carry += 256 * b58[it];
					b58[it] = carry % base;
					carry = (carry / base) >>> 0;
				}

				length = i;
				pbegin++;
			}

			let it = size - length;
			while (it !== size && b58[it] === 0) it++;

			let str = leader.repeat(zeros);
			for (; it < size; ++it) str += alphabet.charAt(b58[it]);

			return str;
		},

		decode(source) {
			if (!source.length) return new Uint8Array(0);

			let psz = 0;
			let zeros = 0; // 定义 zeros 并初始化为 0

			// 计算前导零的数量
			while (psz < source.length && source[psz] === leader) {
				psz++;
				zeros++;
			}

			const size = ((source.length - psz) * (Math.log(base) / Math.log(256)) + 1) >>> 0;
			const b256 = new Uint8Array(size);

			let length = 0; // 定义 length，用于跟踪解码长度
			while (psz < source.length) {
				let carry = baseMap[source.charCodeAt(psz)];
				if (carry === 255) throw new Error('Invalid character');
				let i = 0;
				for (let it = size - 1; (carry !== 0 || i < length) && it !== -1; it--, i++) {
					carry += base * b256[it];
					b256[it] = carry % 256;
					carry = (carry / 256) >>> 0;
				}

				length = i;
				psz++;
			}

			let it = size - length;
			while (it !== size && b256[it] === 0) it++;

			const vch = new Uint8Array(zeros + (size - it));
			vch.fill(0, 0, zeros);

			for (let j = zeros; it !== size; j++, it++) vch[j] = b256[it];

			return vch;
		}
	};
}